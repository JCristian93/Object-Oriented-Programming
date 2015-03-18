
#include "hash.h"
#include "hashtable.h"
#define DIM_LINE	20000
#define	ZERO		0
#define UNU			1

char *read_line(FILE *fp)
{
	char *line, *retfgets;

	line = malloc(sizeof(char) * (DIM_LINE + 1));
	retfgets = fgets(line, DIM_LINE, fp);
	if (NULL == retfgets)
		free(line);
	return retfgets;
}

struct list_node *init(int dim_buckets)
{
	struct list_node *buckets;
	unsigned int i;

	buckets = malloc(sizeof(struct list_node) * (dim_buckets + 1));
	for (i = ZERO; i < dim_buckets; i++) {
		(buckets + i)->next = NULL;
		(buckets + i)->prev = NULL;
		(buckets + i)->word = NULL;
	}
	return buckets;
}

struct list_node *has_word(char *word, struct list_node **buckets, int dim_buckets)
{
	struct list_node *tmp;

	if (NULL == word || NULL == buckets)
		return NULL;
	tmp = *buckets + hash(word, dim_buckets);
	while (NULL != tmp->next) {
		if (NULL != tmp->word && ZERO == strcmp(tmp->word, word))
			return tmp;
		tmp = tmp->next;
	}
	if (NULL != tmp->word && ZERO == strcmp(tmp->word, word))
		return tmp;
	return NULL;
}

void add_word(char *word, struct list_node **buckets, int dim_buckets)
{
	struct list_node *new_word, *tmp;

	if (NULL == word || NULL == buckets)
		return;
	if (NULL != has_word(word, buckets, dim_buckets))
		return;
	new_word = malloc(sizeof(struct list_node));
	new_word->word = malloc(sizeof(char) * strlen(word));
	new_word->word = strcpy(new_word->word, word);
	new_word->next = NULL;
	tmp = *buckets + hash(word, dim_buckets);
	while (NULL != tmp->next)
		tmp = tmp->next;
	tmp->next = new_word;
	new_word->prev = tmp;
}

void remove_word(char *word, struct list_node **buckets, int dim_buckets)
{
	struct list_node *tmp;

	if (NULL == word || NULL == buckets)
		return;
	tmp = has_word(word, buckets, dim_buckets);
	if (NULL == tmp)
		return;
	tmp->prev->next = tmp->next;
	if (tmp->next != NULL)
		tmp->next->prev = tmp->prev;
	free(tmp->word);
	free(tmp);
}

void print_bkt(unsigned int index, struct list_node **buckets, char *filename, int dim_buckets)
{
	FILE *fp;
	struct list_node *tmp;
	unsigned int ok = ZERO;

	if (index >= dim_buckets)
		return;
	tmp = *buckets + index;
	if (NULL == filename) {
		if (NULL != tmp->next) {
			ok = UNU;
			tmp = tmp->next;
			printf("%s", tmp->word);
		}
		while (NULL != tmp->next) {
			tmp = tmp->next;
			printf(" %s", tmp->word);
		}
		if (UNU == ok)
			printf("\n");
		return;
	}
	fp = fopen(filename, "a");
	DIE(NULL == fp, "file4 open failure\n");
	if (NULL != tmp->next) {
		ok = UNU;
		tmp = tmp->next;
		fprintf(fp, "%s", tmp->word);
	}
	while (NULL != tmp->next) {
		tmp = tmp->next;
		fprintf(fp, " %s", tmp->word);
	}
	if (UNU == ok)
		fprintf(fp, "\n");
	fclose(fp);
}

void print(struct list_node **buckets, char *filename, int dim_buckets)
{
	unsigned int i;

	for (i = ZERO; i < dim_buckets; i++)
		print_bkt(i, buckets, filename, dim_buckets);
}

void resize(struct list_node **buckets,
				struct list_node **buckets_nou, int dim_buckets, int dim_noua)
{
	unsigned int i;
	struct list_node *pointer;

	for (i = ZERO; i < dim_buckets; i++) {
		pointer = *buckets + i;
		while (NULL != pointer->next) {
			pointer = pointer->next;
			add_word(pointer->word, buckets_nou, dim_noua);
		}
	}
}

void release_bucket(struct list_node **buckets, int dim)
{
	unsigned int i;
	struct list_node *pointer, *nextp;

	if (NULL == buckets)
		return;
	for (i = ZERO; i < dim; i++) {
		pointer = *buckets + i;
		pointer = pointer->next;
		while (NULL != pointer) {
			nextp = pointer->next;
			free(pointer->word);
			free(pointer);
			pointer = nextp;
		}
	}
}

int process_instr(char *instr, struct list_node **buckets, int dim_buckets)
{
	char *tmp;
	FILE *fp;
	unsigned int i, index;

	for (i = ZERO; i < strlen(instr); i++)
		if (instr[i] == '\n')
			instr[i] = ' ';
	tmp = strtok(instr, " ");
	if (NULL == tmp)
		return -1;
	if (strcmp(tmp, "add") == 0) {
		tmp = strtok(NULL, " ");
		add_word(tmp, buckets, dim_buckets);
		return 0;
	} else if (ZERO == strcmp(tmp, "remove")) {
		tmp = strtok(NULL, " ");
		remove_word(tmp, buckets, dim_buckets);
		return 0;
	} else if (ZERO == strcmp(tmp, "find")) {
		tmp = strtok(NULL, " ");
		if (NULL != has_word(tmp, buckets, dim_buckets)) {
			tmp = strtok(NULL, " ");
			if (NULL == tmp) {
				printf("True\n");
				return 0;
			} else if (NULL != tmp) {
				fp = fopen(tmp, "a");
				DIE(NULL == fp, "file2 open failure\n");
				fprintf(fp, "True\n");
				fclose(fp);
				return 0;
			}
		} else {
			tmp = strtok(NULL, " ");
			if (NULL == tmp) {
				printf("False\n");
				return 0;
			}
			if (NULL != tmp) {
				fp = fopen(tmp, "a");
				DIE(fp == NULL, "file3 open failure\n");
				fprintf(fp, "False\n");
				fclose(fp);
				return 0;
			}
		}
	} else if (ZERO == strcmp(tmp, "clear")) {
		release_bucket(buckets, dim_buckets);
		*buckets = init(dim_buckets);
		return 0;
	} else if (ZERO == strcmp(tmp, "print_bucket")) {
		tmp = strtok(NULL, " ");
		index = atoi(tmp);
		tmp = strtok(NULL, " ");
		print_bkt(index, buckets, tmp, dim_buckets);
		return 0;
	} else if (ZERO == strcmp(tmp, "print")) {
		tmp = strtok(NULL, " ");
		print(buckets, tmp, dim_buckets);
		return 0;
	} else if (ZERO == strcmp(tmp, "resize")) {
		tmp = strtok(NULL, " ");
		if (ZERO == strcmp(tmp, "double")) {
			dim_buckets = dim_buckets * 2;
			return dim_buckets;
		}
		else if (ZERO == strcmp(tmp, "halve")) {
			dim_buckets = dim_buckets / 2;
			return dim_buckets;
		}
	}
	return -1;
}

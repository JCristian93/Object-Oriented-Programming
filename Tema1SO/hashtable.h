
#ifndef HASHTABLE_H_
#define HASHTABLE_H_

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define DIE(assertion, call_description)				\
	do {								\
		if (assertion) {					\
			fprintf(stderr, "(%s, %d): ",			\
					__FILE__, __LINE__);		\
			perror(call_description);			\
			exit(EXIT_FAILURE);				\
		}							\
	} while (0)

struct list_node {
	char *word;
	struct list_node *next;
	struct list_node *prev;
};

char *read_line(FILE *fp);

struct list_node *init(int dim_buckets);

struct list_node *has_word(char *word, struct list_node **buckets, int dim_buckets);

void add_word(char *word, struct list_node **buckets, int dim_buckets);

void remove_word(char *word, struct list_node **buckets, int dim_buckets);

void print_bkt(unsigned int index, struct list_node **buckets, char *filename, int dim_buckets);

void print(struct list_node **buckets, char *filename, int dim_buckets);

void resize(struct list_node **buckets,
				struct list_node **buckets_nou, int dim_buckets, int dim_noua);

void release_bucket(struct list_node **buckets, int dim);

int process_instr(char *instr, struct list_node **buckets, int dim_buckets);

#endif


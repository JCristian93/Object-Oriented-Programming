#include "hashtable.h"
#define ARG_MIN		2
#define ARG_ERR		1

int main(int argc, char *argv[])
{
	char *instr;
	struct list_node *buckets, *buckets_nou;
	unsigned int i, dim_noua, dim_buckets;
	FILE *fp;

	DIE(argc < ARG_MIN, "too few arguments to main\n");
	dim_buckets = atoi(argv[ARG_ERR]);
	DIE(dim_buckets < ARG_ERR, "invalid number of buckets\n");
	buckets = init(dim_buckets);
	if (ARG_MIN == argc) {
		instr = read_line(stdin);
		DIE(NULL == instr, "no stdin input\n");
		while (NULL != instr) {
			if (strcmp(instr, "\n") == 0) {
				free(instr);
				instr = read_line(stdin);
			}
			dim_noua = process_instr(instr, &buckets, dim_buckets);
			if (dim_noua > 0) {
				buckets_nou = init(dim_noua);
				resize(&buckets, &buckets_nou, dim_buckets, dim_noua);
				release_bucket(&buckets, dim_buckets);
				free(buckets);
				buckets = buckets_nou;
				dim_buckets = dim_noua;
			}
			free(instr);
			instr = read_line(stdin);
		}
		goto jmp;
	}
	for (i = ARG_MIN; i < argc; i++) {
		fp = fopen(argv[i], "r");
		DIE(NULL == fp, "file1 open failure\n");
		instr = read_line(fp);
		while (NULL != instr) {
			if (strcmp(instr, "\n") == 0) {
				free(instr);
				instr = read_line(fp);
			}
			dim_noua = process_instr(instr, &buckets, dim_buckets);
			if (dim_noua > 0) {
				buckets_nou = init(dim_noua);
				resize(&buckets, &buckets_nou, dim_buckets, dim_noua);
				release_bucket(&buckets, dim_buckets);
				free(buckets);
				buckets = buckets_nou;
				dim_buckets = dim_noua;
			}
			free(instr);
			instr = read_line(fp);
		}
		fclose(fp);
	}
jmp:
	free(instr);
	release_bucket(&buckets, dim_buckets);
	free(buckets);
	return 0;
}


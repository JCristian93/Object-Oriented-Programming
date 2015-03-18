CC = gcc
CFLAGS = -Wall

build: main.o hashtable.o hash.o
	$(CC) $(CFLAGS) main.o hashtable.o hash.o -o tema1

hash.o: hash.c hash.h
hashtable.o: hashtable.c hashtable.h
main.o: main.c

.PHONY:
clean:
	rm -f *.o
	rm -f tema1

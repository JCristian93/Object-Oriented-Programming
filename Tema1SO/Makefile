# Sisteme de operare
# Tema 1
# Nume: Jalba Cristian
# Grupa: 333 CA

# CC = cl
# CFLAGS = /W3 -D_CRT_SECURE_NO_DEPRECATE

# build:
# 	$(CC) $(CFLAGS) tema1.c hashtable.c linked_list.c hash.c /Fetema1

# clean:
# 	del tema1

CC = cl
CFLAGS = /Wall /wd4127 /nologo -D_CRT_SECURE_NO_DEPRECATE
BIN = tema1.exe

all: build

build: main.obj hashtable.obj hash.obj
	$(CC) $(CFLAGS) /Fetema1.exe main.obj hashtable.obj hash.obj

main.obj: main.c
	$(CC) -c $(CFLAGS) main.c

hashtable.obj: hashtable.h hashtable.c
	$(CC) -c $(CFLAGS) hashtable.c

hash.obj: hash.h hash.c
	$(CC) -c $(CFLAGS) hash.c

.PHONY: clean

clean:
	del /Q /F *.obj
	del /Q /F $(BIN)
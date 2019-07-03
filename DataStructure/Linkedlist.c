#include <stdio.h>
#include <stdlib.h>

typedef struct _node {
	int data;
	struct _node* next;
	struct _node* prev;
} NODE;

NODE* head;
NODE* end;

void init();
void insertNode(NODE* node);
void deleteNode(NODE* node);
NODE* findNode(int data);
void printAll();
NODE* makeNode(int data);

void init() {
	head = (NODE*)malloc(sizeof(NODE));
	end = (NODE*)malloc(sizeof(NODE));

	head->data = 0;
	head->next = end;
	head->prev = NULL;
	end->prev = head;
	end->data = 0;
	end->next = NULL;
	printf("init complete!\n");
}

NODE* makeNode(int data) {
	NODE* tmp = (NODE*)malloc(sizeof(NODE));
	tmp->data = data;
	tmp->next = NULL;
	tmp->prev = NULL;
}

void insertNode(NODE* node) {
	
	NODE* tmp;
	tmp = head->next;

	if (tmp == end) {
		// no list data
		head->next = node;
		node->prev = head;
		end->prev = node;
		node->next = end;
	}
	else {
		while (tmp != NULL) {
			if (tmp->data < node->data) break;
			tmp = tmp->next;
		}
		tmp->next->prev = node;
		node->next = tmp->next;
		tmp->next = node;
		node->prev = tmp;
	}
	
	printf("Successfully inserted!\n");
}

void deleteNode(NODE* node) {
	NODE* tmp;
	tmp = head;
	while (tmp != NULL) {
		if (tmp->data == node->data) break;
		tmp = tmp->next;
	}
	if (tmp == NULL) {
		printf("Node not be found!\n");
		return;
	}
	else {
		printf("deleting Node with data : %d\n", tmp->data);
		tmp->prev->next = tmp->next;
		tmp->next->prev = tmp->prev;

		free(tmp);
	}

}

NODE* findNode(int data) {
	NODE* tmp;
	tmp = head;
	while (tmp != NULL) {
		if (tmp->data == data)break;
		tmp = tmp->next;
	}
	return tmp;
}

void printAll() {
	NODE* tmp;
	tmp = head->next;
	while (tmp->next != NULL) {
		printf("Linked List data : %d\n", tmp->data);
		tmp = tmp->next;
	}
}


int main(void) {
	
	init();
	
	NODE* tmp1 = (NODE*)malloc(sizeof(NODE));
	tmp1->data = 1;
	insertNode(tmp1);
	NODE* tmp2 = (NODE*)malloc(sizeof(NODE));
	tmp2->data = 2;
	insertNode(tmp2);
	NODE* tmp3 = (NODE*)malloc(sizeof(NODE));
	tmp3->data = 3;
	insertNode(tmp3);
	NODE* tmp4 = (NODE*)malloc(sizeof(NODE));
	tmp4->data = 4;
	insertNode(tmp4);
	printAll();
	deleteNode(tmp4);
	NODE* tmp5 = (NODE*)malloc(sizeof(NODE));
	deleteNode(tmp5);
	printAll();


	return 0;
}


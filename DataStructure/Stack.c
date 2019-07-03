#include <stdio.h>
#include <stdlib.h>

typedef struct _node {
	int data;
	struct _node* next;
	struct _node* prev;
} NODE;

NODE* head;
NODE* tail;

void init_stack();
NODE* make_node(int data);
void push_stack(NODE* node);
NODE* pop_stack();
void printAll();

void init_stack() {
	head = (NODE*)malloc(sizeof(NODE));
	tail = (NODE*)malloc(sizeof(NODE));
	head->next = tail;
	head->prev = NULL;
	head->data = NULL;
	tail->next = NULL;
	tail->prev = head;
	tail->data = NULL;
	printf("init success!\n");
}

NODE* make_node(int data) {
	NODE* tmp = (NODE*)malloc(sizeof(NODE));
	tmp->next = NULL;
	tmp->prev = NULL;
	tmp->data = data;
	return tmp;
}

void push_stack(NODE* node) {
	NODE* tmp;
	tmp = head;

	if (head->next == tail) {
		// no data yet
		head->next = node;
		node->prev = head;
		node->next = tail;
		tail->prev = node;
		printf("first push success!\n");
	}
	else {
		// data exist
		head->next->prev = node;
		node->next = head->next;
		node->prev = head;
		head->next = node;
		printf("Push Success!\n");
	}
}

NODE* pop_stack() {
	NODE* tmp;
	if (head->next == tail) {
		printf("NO DATA!\n");
		return NULL;
	}
	else {
		tmp = tail->prev;
		tail->prev->prev->next = tail;
		tail->prev = tail->prev->prev;
		printf("Pop exec with a value : %d\n", tmp->data);
		return tmp;
	}
}

void printAll() {
	NODE* tmp;
	int cnt = 1;
	tmp = head->next;
	while (tmp != tail) {
		printf("Queue data is %d with index num : %d\n", tmp->data, cnt);
		tmp = tmp->next;
		cnt++;
	}
}



int main(void) {
	
	NODE* tmp1, *tmp2, *tmp3, *tmp4, *tmp5;
	tmp1 = make_node(1);
	tmp2 = make_node(3);
	tmp3 = make_node(5);
	tmp4 = make_node(7);
	tmp5 = make_node(9);
	init_stack();

	push_stack(tmp1);
	push_stack(tmp3);
	push_stack(tmp5);
	push_stack(tmp4);
	push_stack(tmp2);
	printAll();
	pop_stack();
	pop_stack();
	pop_stack();
	printAll();


	return 0;
}


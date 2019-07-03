#include <stdio.h>
#include <stdlib.h>

typedef struct _node {
	int data;
	struct _node* next;
	struct _node* prev;
} NODE;

NODE* head;
NODE* tail;

void init_queue();
NODE* make_node(int data);
void push_queue(NODE* node);
NODE* pop_queue();
void printAll();

void init_queue() {
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

void push_queue(NODE* node) {
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

NODE* pop_queue() {
	NODE* tmp;
	if (head->next == tail) {
		printf("NO DATA!\n");
		return NULL;
	}
	else {
		tmp = head->next;
		head->next->next->prev = head;
		head->next = head->next->next;
		printf("Pop exec!\n");
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
	init_queue();

	push_queue(tmp1);
	push_queue(tmp3);
	push_queue(tmp5);
	push_queue(tmp4);
	push_queue(tmp2);
	printAll();
	pop_queue();
	printAll();


	return 0;
}


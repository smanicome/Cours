#include "tree.h"
#include "visualtree.h"
#include <stdio.h>

int main() {

  node *t;
  t = scan_tree();
  /* t = create_node(3);

  t->left = create_node(5);
  t->left->left = create_node(12);
  t->left->right = create_node(1);
  t->left->right->left = create_node(4);

  t->right = create_node(2);
  t->right->right = create_node(7); */

  /* display_prefix(t);
  putchar('\n');
  display_infix(t);
  putchar('\n');
  display_suffix(t);
  putchar('\n'); */

  write_tree(t);

  printf("Nodes: %d\n", count_nodes(t));
  printf("Leaves: %d\n", count_leaves(t));
  printf("Only children: %d\n", count_only_children(t));
  printf("Height: %d\n", height(t) - 1);

  free_tree(t);

  return 0;
}

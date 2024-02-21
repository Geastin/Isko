//Geastin G. Castillo
//CMSC 23 UV-4L
//2021 - 68198
//Exercise 1
//February 16, 2024

void main() {
  //list of variables
  var list = [1, 3, 1, 4, 3, 1, 5, 5, 3, 2, 5, 6, 8];
  //map of arranged list
  var map = Map();
  //empty list
  var product = [];
  
 // for each variable in the list, it registers  a unique key and adds to the value.
  list.forEach((list) {
    if (!map.containsKey(list)) {
      map[list] = 1;
    } else {
      map[list] += 1;
    }
  });

  //iterates through the list and multiplies the keys to the values then adds it to the empty list
  map.forEach((k, v) {
    product.add(k*v);
  });

  //prints the original list and list of arranged occurences.
  print("Original list: $list");
  print("Number of occurences: $map");
  print("Product list: $product");
}


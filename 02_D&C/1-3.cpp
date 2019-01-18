#include <iostream>
  using namespace std;

void insertionSort(int arr[], int n) {
  int i, j, value;

  for (i = 1; i < n; i++) {
    value = arr[i];
    j = i - 1;

    while (j >= 0 && arr[j] > value) {
      arr[j + 1] = arr[j];
      j = j - 1;
    }
    arr[j + 1] = value;
  }
}

void insertionSort(int arr[], int l, int r) {

  int n = r - l + 1;

  // create temp array 
  int temp[n];

  // copy data to temp array
  for (int i = l; i <= r; i++) {

    temp[i - l] = arr[i];

  }

  //Sort the copied array 
  insertionSort(temp, n);

  //Edit the original data
  for (int i = l; i <= r; i++)
    arr[i] = temp[i - l];

}

void merge(int arr[], int l, int m, int r) {
  int i, j, index;
  int n1 = m - l + 1;
  int n2 = r - m;

  // create temp arrays 
  int L[n1], R[n2];

  //copy data to temp arrays L[] and R[] 
  for (i = 0; i < n1; i++)
    L[i] = arr[l + i];
  for (j = 0; j < n2; j++)
    R[j] = arr[m + 1 + j];

  // initializing indexes
  i = 0;
  j = 0;
  index = l;

  while (i < n1 && j < n2) {
    if (L[i] <= R[j]) {
      arr[index] = L[i];
      i++;
    } else {
      arr[index] = R[j];
      j++;
    }
    index++;
  }

  // Copy the remaining elements     
  while (i < n1) {
    arr[index] = L[i];
    index++;
    i++;
  }
  while (j < n2) {
    arr[index] = R[j];
    index++;
    j++;
  }

}

void mergeSort(int arr[], int l, int r, int depth, int limit) {
  //Within acceptable depth and indexes
  if (l < r && depth <= limit) {
    // Same as (l+r)/2, but avoids overflow for 
    // large l and h 
    int m = l + (r - l) / 2;

    // Sort first and second halves 
    mergeSort(arr, l, m, depth + 1, limit);
    mergeSort(arr, m + 1, r, depth + 1, limit);

    merge(arr, l, m, r);
  } else if (l < r) {
    insertionSort(arr, l, r);
  }
}

//Testing 
int main() {
  int n;
  cout << "Give the size of the array:";
  cin >> n;
  //A random array
  int arr[n];

  //Generate reverse array
  for (int i = n - 1; i >= 0; i--) {
    cout << i << " ";
    arr[i] = i;
  }
  cout << endl;

  //Limit to the depth
  int r;
  cout << "Give the maximum depth before performing insertion sort:";
  cin >> r;

  mergeSort(arr, 0, n - 1, 1, r);

  //Print the sorted array
  cout << endl;
  for (int i = 0; i < n; i++) {
    cout << arr[i] << " ";
  }
  cout << endl;

  return 0;
}

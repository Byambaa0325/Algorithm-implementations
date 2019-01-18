#include<iostream>
using namespace std;

/*
	Finding min and max using Divide and Conquer approach
	Intuition: Divide the array into single parts then merge while taking only max or min of the merging values.
	Time complexity = O(n);

*/

void printArray(int arr[], int n)
{
   int i;
   for (i=0; i < n; i++)
       printf("%d ", arr[i]);
   printf("\n");
}
int mergeMax(int a, int b){
	
	if(a<b){
		return b;
	}
	return a;
}
int mergeMin(int a, int b){
	if(a<b){
		return a;
	}
	return b;
}
int minDC(int arr[],int l, int r){
	if(l==r){
		return arr[l];}
	return mergeMin(minDC(arr,l,(l+r)/2),minDC(arr,(l+r)/2+1,r));
}
int maxDC(int arr[],int l, int r){
	
	if(l==r){
	
		return arr[r];}
	int a=maxDC(arr,l,(l+r)/2);
	
	int b=maxDC(arr,(l+r)/2+1,r);
	
	
	return mergeMax(a,b);
}

int minimax(int arr[], int n){
	   
	int min= minDC(arr,0,n);
	int max= maxDC(arr,0,n);
	cout<<"min= "<<min <<" max= "<<max<<endl;
}
int main(){
	int n;
	cin>>n;
	int array[n];
	for (int i=0;i<n;i++){
		array[i]=i;
	}
	printArray(array,n);
	int min,max;
    minimax(array,n-1);
	
}

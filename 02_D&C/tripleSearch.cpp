#include <iostream>
using namespace std;



int search(int l, int h, int value,int arr[]){


	//terminative case
	if(l>=h){
		return -1;
	}
	
	//base case
	int point1= ((h-l)/3)*1+l;
	int point2= ((h-l)/3)*2+l;
	
	if(arr[point1]==value){
		return point1;
	}
	if(arr[point2]==value){
		return point2;
	}
	
	//recursive  case
	if(arr[point1]<value){
		if(arr[point2]<value){
		return search(point2,h,value,arr);
	}
		else{
			return search(point1,point2,value,arr);
			
		}
	}
	else {
		return search(l,point1,value,arr);
		
	}
	
	
}
int main(){
	
	int n; 
	cin>>n;
	int arr[n];
	for(int i=0;i<n;i++){
		arr[i]=i;
	}
	int value;
	cin>>value;
	int index = search(0,n,value,arr);
	cout<<"The index is "<<index<<endl;

}

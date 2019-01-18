#include <iostream>
using namespace std;


int search(int arr[], int l, int r){	
	
	int mid= (l+(r-l)/2); // avoid integer overflow	
	
	if(arr[l]>arr[r]){
		//Check if mid is max
		if(arr[mid]>arr[mid+1]){
			return mid;
		}
		else{
			//Search both sides, but terminate if it is in order, either one is called!!!
			if(arr[mid]>arr[r]){
			//Right Side			
				return search(arr,mid,r);
			}
			else{		
			//Left Side			
			return search(arr,l,mid);
			}			
		}
	}
	else{
	//Not shifted
	return r+1;	
	}
}
int main(){
	// For getting input from input.txt file
    freopen("input.txt", "r", stdin);
 
    // Printing the Output to output.txt file
    freopen("output.txt", "w", stdout); 
	
	// Get user input...
	int n; 
	cin>>n;
	int arr[n];
	for(int i=0;i<n;i++){
		cin>>arr[i];
	}
	
	//Print the result
	cout<< 	arr[search(arr,0,n-1)]<< endl;
	return 0;
}

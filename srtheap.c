/** File: srtheap.c
 *  Professor: Professor Kapleau
 *  Name: Rudra Mehta (rm279@njit.edu)
 *  Course: CS280
 *  Section: 011
 *
 *  This program will perform the heapsort algorithm to sort an array of floating-point numbers. 
 *  Explanatory comments are provided below.
 */

//kFLFBx9T7XJmrr3p2F/fmAj+8DS8GqnKeq/TOIAPcuI=

#include <stdio.h>
#include "srt.h"

//heapify() - generates a maxheap out of the array of numbers provided
void heapify(void *array, size_t parentIndex, size_t sz, size_t size, int (* compar)(const void *, const void *)){
    if(parentIndex*2 + 1 < sz){
        size_t biggest = parentIndex*2 + 2 < sz && compar(array + (parentIndex*2 + 1)*size, array + (parentIndex*2 + 2)*size) < 0? parentIndex*2 + 2 : parentIndex*2 + 1;
        
        //recursive call to mend the maxheap after a swap is performed
        if(compar(array + biggest*size, array + parentIndex*size) > 0){
            char temp[size];
            memcpy(temp, array + biggest*size, size);
            memcpy(array + biggest*size, array + parentIndex*size, size);
            memcpy(array + parentIndex*size, temp, size);

            heapify(array, biggest, sz, size, compar);
        }
    }
}

//srtheap() - a sorting algorithm that creates, breaks, and mends max-heaps until the array is sorted
void srtheap(void *array, size_t nelem, size_t size, int (*compar)(const void *, const void *)){
    //maxHeap builder
    for(int parentIndex = (nelem -2)/2; parentIndex >= 0; --parentIndex)
        heapify(array, parentIndex, nelem, size, compar);
    
    //reheapifier
    for(int sz = nelem; sz > 0; heapify(array, 0, sz, size, compar)){
        //Swaps root with last available index, effectively breaking the max-heap
        char temp[size];
        memcpy(temp, array, size);
        memcpy(array, array + (sz -1)*size, size);
        memcpy(array + (sz---1)*size, temp, size);
    }
}
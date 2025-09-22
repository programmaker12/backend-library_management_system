package com.thinkinnovative.library_management_system.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SortController {
    static void swap(int [] array, int low, int high) {
        int temp = array[low];
        array[low] = array[high];
        array[high] = temp;
    }

        @PostMapping("/sort")
        public int[] sort(@RequestBody int[] array,  int low, int high) {
            //java.util.Arrays.sort(array);

            high = array.length - 1;
            low = 0;
            for (int j = low; j < high-1; j++) {
                if (array[j] < array[high]) {
                    swap(array, low, high);
                    low++;
                }
            }
            swap(array, low, high);
            
            return array;
        }



}


# **Assignment 1 — Divide and Conquer Algorithms**

## **Objectives**

* Implement classical *divide and conquer* algorithms.
* Control recursion depth and runtime.
* Collect metrics and compare them with theoretical complexity.
* Use GitHub with branches and a clear commit history.

---

## **Project Structure**

* **Metrics** — measures time, recursion depth, and number of comparisons.
* **ArrayUtils** — helper functions (`swap`, `partition`, `merge`).
* **Algorithms:**

  * MergeSort
  * QuickSort
  * Deterministic Select (Median of Medians)
  * Closest Pair of Points

---

## **Algorithms**

### **MergeSort**

* Divides the array into two halves, sorts them recursively, and merges.
* Uses a shared buffer and cutoff threshold for small arrays.
* **Complexity:** `O(n log n)`

### **QuickSort**

* Chooses a random pivot.
* Always recurses first on the smaller partition to ensure `O(log n)` depth.
* **Average complexity:** `O(n log n)`

### **Deterministic Select (Median of Medians)**

* Divides the array into groups of 5 elements.
* Finds the median of medians and recurses only in the relevant part.
* **Complexity:** `O(n)`

### **Closest Pair of Points**

* Sorts points by X-coordinate, splits in half, and checks a narrow “strip” by Y.
* For each point, checks at most 7–8 nearby points.
* **Complexity:** `O(n log n)`

---

## **Metrics and CSV Output**

Each algorithm writes a record after execution:

```
algorithm,n,time_ms,depth,comparisons
QuickSort,1024,1.53,16,2000
MergeSort,1024,1.72,11,2048
```

---

## **Results**

| Algorithm    | Theoretical | Measured | Match |
| ------------ | ----------- | -------- | ----- |
| MergeSort    | O(n log n)  | Yes      |      |
| QuickSort    | O(n log n)  | Yes      |      |
| Select       | O(n)        | Yes      |      |
| Closest Pair | O(n log n)  | Yes      |     |

---

## **Git Workflow**

### **Main branches:**

* `main` — stable version
* `feature/mergesort`
* `feature/quicksort`
* `feature/select`
* `feature/closest`
* `feature/metrics`
* `feature/cli`

### **Commit history:**

```
init: maven, junit5, readme
feat(metrics): counters and csv
feat(mergesort): base + buffer + cutoff
feat(quicksort): randomized pivot + safe recursion
feat(select): median of medians
feat(closest): divide and conquer
feat(cli): run algos and export csv
release: v1.0
```

---

## **Conclusion**

* All algorithms work correctly and fit within `O(log n)` recursion depth.
* Collected metrics confirm theoretical time complexity.
* Project follows GitHub standards and is ready for presentation.

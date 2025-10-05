![License](https://img.shields.io/badge/java-green)
![Java](https://img.shields.io/badge/maven-blue)
![Python](https://img.shields.io/badge/python-yellow)
![JUnit5](https://img.shields.io/badge/JUnit5-red)


# ![gif](https://github.com/Readme-Workflows/Readme-Icons/blob/main/icons/gifs/wave.gif) Assignment 2
_In /docs/Analysis_Nurtilet_ofAlgorithmMansur.pdf you can find Nurtilek's analysis_

<br>

### Boyer-Moore Majority Vote Algorithm. Serikbai Mansur's analysis

## ⚙️ Clone the Repository

```
https://github.com/Mansurrv/assingment2-BoyerMooreMajorityVote
```

<br>

## 🖼 Diagrams
>The **Boyer-Moore Majority Vote Algorithm** is an elegant and highly efficient method for finding the **Majority Element** in an array. A Majority Element is defined as an element that appears **more than _N/2_ times** in an array of length _N_.

![Algorithms](docs/performance-plots/compare_performance.png)
![Algorithms](docs/performance-plots/compare_algorithm_scalibility.png)

## Project Structure

```text
assingment2-BoyerMooreMajorityVote/
│
├── main/
│   └── java/
│       └── org.example/
│           ├── algorithms/
│           │   └── BoyerMooreMajorityVoteAlgorithm.java   # Main algorithm implementation
│           ├── cli/
│           │   └── BenchmarkRunner.java                  # CLI for benchmarking
│           └── metrics/
│               └── Metrics.java                           # Metrics tracking class
│
├── resources/                                           # Additional resources
├── test/
│   └── java/
│       └── org.example.algorithms/
│           └── BoyerMooreMajorityVoteAlgorithmTest.java # Unit tests
├── performance-plots/
│   ├── compare_algorithm_scalibility.png
│   └── compare_performance.png
├── Cross-Review-Summary/
│   └── Cross-Review Summary.pdf                          # Partner comparison
├── target/                                              # Build outputs
├── benchmark-results.csv                                 # CSV with benchmark data
├── benchmark results.csv                                 # Alternate CSV
├── main.py                                              # Python benchmarking script
├── second.py                                            # Additional Python script
├── README.md                                            # Project overview and instructions
└── .gitignore                                           # Git ignore file

```

<br>

## 🛠 Technology stack
- Java Core
- Python
- JUnit5
- Maven

<br>
<br>

# 1. Key Features

### 1.1 **Linear Time:**

The complexity is _O(N)_, as the algorithm requires only a single pass through the array.

### 1.2 **Constant Space:**

The space complexity is _O(1)_, using only two variables (`candidate` and `count`) regardless of the input size.

<br>
<br>

# 2. Complexity Analysis

| Metric | Complexity | Notes |
| :--- |:-----------| :--- |
| **Time** | _O(N)_     | Single pass over the input array. |
| **Space** | _O(1)_     | Constant memory usage. |

#### Performance Comparison (Baseline vs Optimized)

My benchmark tests compare the performance of the **optimized** version against the **baseline** implementation as the input size (_N_) increases.

### Summary at Maximum Input Size (_N = 100000_)

| Version | Time (ns) | Comparisons |
| :--- | :--- | :--- |
| baseline | 2775707 | 200000 |
| optimized | 2964544 | 155489 |

### Key Findings
* **Comparisons:** The optimized version performs **44,511 fewer** comparisons (at _N=100000_). This demonstrates a successful reduction in the constant factor of the algorithm's complexity.
* **Time:** At the largest input size (_N=100000_), the optimized version is **188,837 ns slower** compared to the baseline. While the optimized version is faster for small _N_, the slight increase in time at large $N$ might be due to overhead from the optimization logic itself.

## 🛠 How It Works
The algorithm relies on the principle that if an element _X_ is a majority element, it remains the majority even after removing any pair _(X, Y)_ where _Y_ is not equal to _X_.

1.  **Initialization:** Select the first element as the `candidate` and set the `count` to 1.
2.  **Iteration:** For each subsequent element in the array:
    * If the **`count` is 0**, the current element becomes the new `candidate`, and `count` is reset to 1.
    * If the current element **matches the `candidate`**, increment `count`.
    * If the current element **does not match the `candidate`**, decrement `count`.
3.  **Final Check:** After the first pass, the `candidate` is the only possible majority element. A **second pass is required** to verify that the element truly occurs more than _N/2_ times (in case no majority element exists).

<br>

## 👨‍💻 Developer
- Mansur Serikbai (@mansurrvv)
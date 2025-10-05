import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import numpy as np

# 1. Загрузка данных из файла
file_path = 'benchmark-results.csv'
df = pd.read_csv(file_path)
sns.set_style("whitegrid")
fig, axes = plt.subplots(1, 2, figsize=(16, 6))
fig.suptitle(
    'Compare algorithm scalability (Baseline vs Optimized)',
    fontfamily="Montserrat",
    fontsize=16,
    y=1
)

sns.lineplot(
    ax=axes[0],
    data=df,
    x='n',
    y='time(ns)',
    hue='version',
    marker='o',
    palette={'baseline': 'navy', 'optimized': 'red'}
)
axes[0].set_title('Time complexity', fontfamily="Montserrat", fontsize=14)
axes[0].set_xlabel('Input size (n)', fontfamily="Montserrat", fontsize=12)
axes[0].set_ylabel('Time (nanoseconds)', fontfamily="Montserrat", fontsize=12)
axes[0].set_xscale('log')
axes[0].set_yscale('log')
axes[0].legend(title='Version')
axes[0].grid(True, which="both", ls="--", linewidth=0.5)


sns.lineplot(
    ax=axes[1],
    data=df,
    x='n',
    y='comparisons',
    hue='version',
    marker='o',
    palette={'baseline': 'navy', 'optimized': 'red'}
)
axes[1].set_title('Number of comparisons', fontfamily="Montserrat", fontsize=14)
axes[1].set_xlabel('Input size (n)', fontsize=12)
axes[1].set_ylabel('Number of comparisons', fontfamily="Montserrat", fontsize=12)
axes[1].set_xscale('log')
axes[1].set_yscale('log')
axes[1].legend(title='Version')
axes[1].grid(True, which="both", ls="--", linewidth=0.5)

plt.tight_layout()
plt.show()
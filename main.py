import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

file_path = 'benchmark_results.csv'

df = pd.read_csv(file_path)

df_10k = df[df['n'] == 10000]

sns.set_style("whitegrid")
fig, axes = plt.subplots(1, 2, figsize=(18, 6))
fig.suptitle(
    'Compare performance: n=10000',
fontfamily="Montserrat",
fontsize=16
)

sns.barplot(
    ax=axes[0],
    data=df_10k,
    x='distribution',
    y='time(ns)',
    hue='version',
    palette={'Baseline': 'skyblue', 'Optimized': 'darkorange'}
)
axes[0].set_title('Time complexity', fontfamily="Montserrat", fontsize=14)
axes[0].set_xlabel('Data distribution', fontfamily="Montserrat", fontsize=12)
axes[0].set_ylabel('time (ns)', fontfamily="Montserrat", fontsize=12)
axes[0].legend(title='Version')

sns.barplot(
    ax=axes[1],
    data=df_10k,
    x='distribution',
    y='memory(bytes)',
    hue='version',
    palette={'Baseline': 'skyblue', 'Optimized': 'darkorange'}
)
axes[1].set_title('Space complexity', fontfamily="Montserrat", fontsize=14)
axes[1].set_xlabel('Data distribution', fontfamily="Montserrat", fontsize=12)
axes[1].set_ylabel('Space (b)', fontfamily="Montserrat", fontsize=12)
axes[1].legend(title='Version')

plt.tight_layout(rect=[0, 0, 1, 0.96])
plt.show()
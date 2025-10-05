import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

file_path = 'benchmark_results.csv'
df = pd.read_csv(file_path)

df['version'] = df['version'].str.strip()

df_10k = df[df['n'] == 10000]

print("Versions found:", df_10k['version'].unique())

sns.set_style("whitegrid")
fig, axes = plt.subplots(1, 2, figsize=(18, 6))
fig.suptitle('Compare performance: n=10000', fontsize=16, fontweight='bold')

sns.barplot(
    ax=axes[0],
    data=df_10k,
    x='distribution',
    y='time(ns)',
    hue='version',
    palette=sns.color_palette(['skyblue', 'darkorange'])
)
axes[0].set_title('Time complexity', fontsize=14, fontweight='bold')
axes[0].set_xlabel('Data distribution', fontsize=12)
axes[0].set_ylabel('Time (ns)', fontsize=12)
axes[0].legend(title='Version')

sns.barplot(
    ax=axes[1],
    data=df_10k,
    x='distribution',
    y='memory(bytes)',
    hue='version',
    palette=sns.color_palette(['skyblue', 'darkorange'])
)
axes[1].set_title('Memory usage', fontsize=14, fontweight='bold')
axes[1].set_xlabel('Data distribution', fontsize=12)
axes[1].set_ylabel('Memory (bytes)', fontsize=12)
axes[1].legend(title='Version')

plt.tight_layout(rect=[0, 0, 1, 0.96])
plt.show()

---


---

<h1 id="genetic-algorithm-function-optimization-problems">Genetic Algorithm: Function optimization problems</h1>
<p>Zhaojie Liu &amp;&amp; Hanbai Zhao<br>
Final Draft: April 11, 2018</p>
<h2 id="problem">Problem:</h2>
<p>I created a genetic algorithm to solve the optimal solution problem of complex functions (within a certain range). The problem poses the situation: Given a complex function, find his optimal solution and return the minimum value of the function. The minimum value of a function may not be an integer, but it will be infinitely close to a certain value, and I will turn it into a table to prove.</p>
<h2 id="implementation-design">Implementation Design:</h2>
<p><strong>1. Genetic code:</strong> Each base is one corresponding to a binary number 0 or 1. Each gene is a string of binary symbols. I’m using binary encoding because I think binary encoding conforms to the principle of minimum character set encoding and is easy to analyze using pattern theorem. What’s more, Binary can be very intuitive to show crossover and mutation (The mutual change of 0 and 1).</p>
<p><strong>2. Gene expression:</strong> x1 and x2 are two variables, and the change interval is [0-6]. A chromosome consists of 46 bases. The first 23 bits of the binary number are the binary strings of x, and the last 23 bits are the binary strings of y.</p>
<p><strong>3. Fitness function:</strong> Since I am evaluating for the minimum of a function, the fitness function is the original function. Calculate the fitness value of each individual on the population. The minimum value in the population is recorded by the comparison method, which is the optimal solution.</p>
<p><strong>4. sort function:</strong> Since I am evaluating for the minimum of a function, the fitness function is the original function. Thus, I should disqualify individuals with large fitness. I am using TreeMap method, in descending order of fitness values, Finds the minimum value in the array using firstEntry ().</p>
<p><strong>5. Crossing Over:</strong> Single-point crossover: The mutation probability is set to 0.6, and I set a breakpoint for chromosome crossover.<br>
<strong>Example:</strong><br>
<strong>Before:</strong>  x1=&lt;0101|0000&gt;<br>
<strong>After:</strong>     x1=&lt;0101|1111&gt;</p>
<p>**6. Mutation: ** The principle of my mutation is to select the mutation site by the mutation probability and change the binary bit.<br>
<strong>Example:</strong><br>
**Before: ** a= &lt;10110010001111110 <strong>0</strong>      0111110111100000010101001111&gt;<br>
<strong>After:</strong>  b=&lt;10110010001111110 <strong>1</strong>    0111110111100000010101001111&gt;</p>
<p><strong>8. Evolution:</strong><br>
For each generation, all individuals sexually reproduce, doubling the population, and each child experiences the two mutation methods. I then calculate the fitness function for each individual and each person’s cumulative probability. I use Roulette Wheel Selection to select people with high fitness who will be selected for the next generation. The remaining individuals then reproduce, doubling the population again.</p>
<p>(1). Generate initial generations, calculate their fitness, and sort. (2). then high fitness of the individuals was kept for mutation and crossover, there is the second generation. (3). The evolutionary process terminates after 2000 generations or when the best fitness score hasn’t changed in 900 generations. (4). Output optimal solution.</p>


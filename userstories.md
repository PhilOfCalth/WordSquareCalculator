# User Stories
Stories that allow the task to be broken up into manageable portions with testable acceptance criteria. Usually these would be viewable on a kanban board implementation of some form and might even be used to communicate with product owners/customers, I'll just drop thoughts and comments here as I go.

## API User can Generate Word Square From Valid Arguments
As a developer using the API I can pass in arguments into the program in the following format:
```
4 eeeeddoonnnsssrv
```
Where the first argument is the length of each word in the word square and the second argument are the letters that can be word. These arguments will be compared against the internal dictionary and turned into a word square which will be returned as an array of Strings
### Acceptance
1. Valid arguments can be passed to the API
2. Number of acceptable characters is exactly the number of characters per word squared
  `Note: Low hanging fruit added by me`
3. Dictionary is stored in memory
4. Only words of the correct length are stored in the memory
5. Only words containing appropriate characters are stored in memory
6. Stored words can be searched by prefix
7. A tree is created and destroyed dynamically to search for solutions
8. Each node of the tree after the root represents another word in the word square
9. The list of child nodes are created by searching the dictionary with prefix created by the nodes parents
10. New words that cannot be made with the remaining characters are pruned straight away
11. The tree is created in a depth first fashion and has found a solution the first time the depth of a node = the number of characters per word

### Notes
 - Possible Optimisations:
   1. The only words we care about in the dictionary are of appropriate length (acceptance 4) and composition (acceptance 5)
   2. If there are an odd number of any one character they must go on the diagonal
   3. If the number of odd numbered characters is not equal to the number of characters in each word, the surplus must be duplicates. `Taking advantage of this will probably be more computationally complex than just searching the rest of the tree 'brute force'`
   4. When a word is added to the cube it uses up characters for it's placements vertically and horizontally. EG If the first word at the top row (and left column) is 'apple' it will use up 1 'a', 4 'p's, 2 'l's and 2 'e's. If the second word is 'phily' it will use up 1 'h', 2 'i's, 2 'l's and 2 'y's. Etc
 - I start working on some pattern matching for acceptance 6, but then I though considering the size of the dictionary after the optimisations in 4 and 5 the extra complexity isn't worth it. Might be something worth benchmarking in a larger app.
 - I don't truly need a full tree datastructure. I just need to traverse the data/posabilities as if I was in one. Unsuccessful branches can be deleted when found and unsearched ones are not yet constructed.
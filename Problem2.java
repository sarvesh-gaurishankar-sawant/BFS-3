/*
301. Remove Invalid Parentheses
Ran on leetcode: Yes
TC: n * 2^n
SC: n * 2^n
Using BFS at each level we create all the possible combination by removing one character and when we find the valid combination in particular level we stop continuing to the next level and only check if there is anyother valid combination on the same level and add them to the result.
*/
class Solution {
    List<String> result;

    public List<String> removeInvalidParentheses(String s) {
        this.result = new ArrayList<>();    
        bfs(s);
        return result;
    }

    private void bfs(String s){
        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        queue.offer(s);
        set.add(s);
        boolean flag = false;

        while(!queue.isEmpty()){
            int size = queue.size();
            
            for(int i = 0; i < size; i++){
                String temp = queue.poll();
                if(isValid(temp)){
                    result.add(temp); // String is valid add it to the result
                    flag = true; // Set the flag as true so that we dont move to next level
                } else {
                    if(!flag){
                        // Remove one bracket from string
                        for(int j = 0; j < temp.length(); j++){
                            if(!Character.isAlphabetic(temp.charAt(j))){
                                String newString = temp.substring(0, j) + temp.substring(j + 1); // Create all the combination by removing one character
                                if(!set.contains(newString)){ // if not visited yet add it to the queue and set
                                    queue.offer(newString);
                                    set.add(newString);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Check if the string is valid parentheses
    private boolean isValid(String s){
        int count = 0;
        for(Character c: s.toCharArray()){
            if(c == '('){ // add one for open brace
                count++;
            } else if(c == ')') { // remove one for closing brackets
                count--;
            }
            if(count < 0) return false; // false if the order is invalid
        }

        if(count == 0) return true; // if the count is 0 there is valid parenthesis

        return false; // else return false
    }
}
class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {};
        
        int i = 0, j = 0;
        int sum = sequence[0];
        int min = Integer.MAX_VALUE;
        while(i <= j) {
            // System.out.println(i + " " + j + " " + sum);
            if(sum < k) {
                j++;
                if(j >= sequence.length) break;
                sum += sequence[j];
            } else if (sum == k) {
                if(j - i < min) {
                    min = j - i;
                    answer = new int[] {i, j};
                }
                sum -= sequence[i];
                i++;
            } else {
                sum -= sequence[i];
                i++;
            }
        }
        return answer;
    }
}
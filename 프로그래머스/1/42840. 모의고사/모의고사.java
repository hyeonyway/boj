import java.util.*;

class Solution {
    public List solution(int[] answers) {
        List<Integer> ans = new ArrayList<>();
        int[] pattern1 = {1,2,3,4,5};
        int[] pattern2 = {2,1,2,3,2,4,2,5};
        int[] pattern3 = {3,3,1,1,2,2,4,4,5,5};
        int cnt1 = 0;
        int cnt2 = 0;
        int cnt3 = 0;
        for(int i = 0; i < answers.length; i++) {
            int answer = answers[i];
            if(pattern1[i % 5] == answer) {
                cnt1++;
            }
            if(pattern2[i % 8] == answer) {
                cnt2++;
            }
            if(pattern3[i % 10] == answer) {
                cnt3++;
            }
        }
        
        int max = Math.max(Math.max(cnt1, cnt2), cnt3);
        if(max == cnt1) {
            ans.add(1);
        }
        if(max == cnt2) {
            ans.add(2);
        }
        if(max == cnt3) {
            ans.add(3);
        }
        
        return ans;
    }
}
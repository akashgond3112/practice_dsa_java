package main.revision.october.meta.medium;

import java.util.*;

public class AccountsMerge {

    class Solution {
        private Map<String, Integer> emailIdx = new HashMap<>(); // Email ka index store karne ke liye map
        private List<String> emails = new ArrayList<>(); // Unique emails ka list
        private Map<Integer, Integer> emailToAcc = new HashMap<>(); // Email index se account index map karne ke liye
        private List<List<Integer>> adj; // Graph ke adjacency list
        private Map<Integer, List<String>> emailGroup = new HashMap<>(); // Account id ke corresponding email groups
        private boolean[] visited; // Visited array for DFS

        public List<List<String>> accountsMerge(List<List<String>> accounts) {

            int n = accounts.size(); // Total accounts ka size
            int m = 0; // Unique emails ka count

            // Step 1: Build email index
            for (int index = 0; index < n; index++) {
                List<String> accountList = accounts.get(index);

                for (int acc = 1; acc < accountList.size(); acc++) { // First element name hota hai, isliye 1 se start

                    String curEmail = accountList.get(acc);

                    if (!emailIdx.containsKey(curEmail)) { // Agar email pehle nahi mila
                        emails.add(curEmail); // Email list me add karo
                        emailIdx.put(curEmail, m); // Email ka index map karo
                        emailToAcc.put(m, index); // Email index ko account index se map karo
                        m++; // Unique email count badhao
                    }
                }
            }

            // Step 2: Build adjacency list
            adj = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                adj.add(new ArrayList<>()); // Har email ke liye empty list
            }

            for (List<String> acc : accounts) {
                for (int i = 2; i < acc.size(); i++) { // Graph banane ke liye emails ko connect karo
                    int idx1 = emailIdx.get(acc.get(i));
                    int idx2 = emailIdx.get(acc.get(i - 1));

                    adj.get(idx1).add(idx2); // Dono directions me edge add karo
                    adj.get(idx2).add(idx1);
                }
            }

            visited = new boolean[m]; // Visited array initialize karo

            // Step 3: DFS traversal
            for (int i = 0; i < m; i++) {
                if (!visited[i]) { // Agar node visited nahi hai
                    int acctid = emailToAcc.get(i); // Account id lo
                    emailGroup.putIfAbsent(acctid, new ArrayList<>()); // Email group initialize karo
                    dfs(i, acctid); // DFS call karo
                }
            }

            // Step 4: Build result
            List<List<String>> result = new ArrayList<>();
            for (int acctId : emailGroup.keySet()) {
                List<String> group = emailGroup.get(acctId); // Email group lo
                Collections.sort(group); // Emails ko sort karo

                List<String> merged = new ArrayList<>();
                merged.add(accounts.get(acctId).get(0)); // Account ka name add karo
                merged.addAll(group); // Sorted emails add karo
                result.add(merged); // Result me add karo
            }

            return result; // Final merged accounts return karo
        }

        private void dfs(int node, int acctId) {

            visited[acctId] = true; // Node ko visited mark karo
            emailGroup.get(acctId).add(emails.get(node)); // Email group me add karo

            for (int neighbor : adj.get(node)) { // Neighbors ke liye DFS call karo
                if (!visited[neighbor]) {
                    dfs(neighbor, acctId);
                }
            }
        }
    }

    // revised on 11/26/2025
    class SolutionRevisonOnthirdDay {
        private Map<String, Integer> emailIdx = new HashMap<>();
        private List<String> emails = new ArrayList<>();
        private Map<Integer, Integer> emailToAcc = new HashMap<>();
        private List<List<Integer>> adj;
        private Map<Integer, List<String>> emailGroup = new HashMap<>();
        private boolean[] visited;

        public List<List<String>> accountsMerge(List<List<String>> accounts) {

            int n = accounts.size();
            int m = 0;

            for (int index = 0; index < n; index++) {
                List<String> accountList = accounts.get(index);

                for (int acc = 1; acc < accountList.size(); acc++) {

                    String curEmail = accountList.get(acc);

                    if (!emailIdx.containsKey(curEmail)) {
                        emails.add(curEmail);
                        emailIdx.put(curEmail, m);
                        emailToAcc.put(m, index);
                        m++;
                    }
                }
            }

            adj = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                adj.add(new ArrayList<>());
            }

            for (List<String> acc : accounts) {
                for (int i = 2; i < acc.size(); i++) {
                    int idx1 = emailIdx.get(acc.get(i));
                    int idx2 = emailIdx.get(acc.get(i - 1));

                    adj.get(idx1).add(idx2);
                    adj.get(idx2).add(idx1);
                }
            }

            visited = new boolean[m];

            for (int i = 0; i < m; i++) {
                if (!visited[i]) {
                    int accid = emailToAcc.get(i);
                    emailGroup.putIfAbsent(accid, new ArrayList<>());
                    dfs(i, accid);
                }
            }

            List<List<String>> result = new ArrayList<>();

            for (int accId : emailGroup.keySet()) {
                List<String> group = emailGroup.get(accId);

                Collections.sort(group);

                List<String> merged = new ArrayList<>();
                merged.add(accounts.get(accId).get(0));
                merged.addAll(group);
                result.add(merged);
            }

            return result;
        }

        private void dfs(int node, int acctId) {

            visited[acctId] = true;

            emailGroup.get(acctId).add(emails.get(node));

            for (int nei : adj.get(node)) {
                if (!visited[nei])
                    dfs(nei, acctId);
            }
        }
    }

    class SolutionRevisonOnSeventhDay {
        private Map<String, Integer> emailIdx = new HashMap<>();
        private List<String> emails = new ArrayList<>();
        private Map<Integer, Integer> emailToAcc = new HashMap<>();
        private List<List<Integer>> adj;
        private Map<Integer, List<String>> emailGroup = new HashMap<>();
        private boolean[] visited;

        public List<List<String>> accountsMerge(List<List<String>> accounts) {

            int n = accounts.size();
            int m = 0;

            // build eamil index
            for (int index = 0; index < n; index++) {
                List<String> accountList = accounts.get(index);

                for (int acc = 0; acc < accountList.size(); acc++) {
                    String curEmail = accountList.get(acc);

                    if (!emailIdx.containsKey(curEmail)) {
                        emails.add(curEmail);
                        emailIdx.put(curEmail, m);
                        emailToAcc.put(m, index);
                        m++;
                    }
                }
            }

            // build adj list

            adj = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                adj.add(new ArrayList<>());
            }

            for (List<String> acc : accounts) {
                for (int i = 2; i < acc.size(); i++) {
                    int idx1 = emailIdx.get(acc.get(i));
                    int idx2 = emailIdx.get(acc.get(i - 1));

                    adj.get(idx1).add(idx2);
                    adj.get(idx2).add(idx1);
                }
            }

            visited = new boolean[m];

            // dfs
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    int acctId = emailToAcc.get(i);
                    emailGroup.putIfAbsent(acctId, new ArrayList<>());
                    dfs(i, acctId);
                }
            }

            // build result
            List<List<String>> result = new ArrayList<>();
            for (int acctId : emailGroup.keySet()) {
                List<String> group = emailGroup.get(acctId);
                Collections.sort(group);

                List<String> merged = new ArrayList<>();
                merged.add(accounts.get(acctId).get(0));
                merged.addAll(group);
                result.add(merged);
            }

            return result;
        }

        private void dfs(int node, int acctId) {

            visited[acctId] = true;
            emailGroup.get(acctId).add(emails.get(node));

            for (int nei : adj.get(node)) {
                if (!visited[nei]) {
                    dfs(nei, acctId);
                }
            }
        }
    }

    // Revised on 12/16/2025
    class SolutionRevisonOnFourtenDay {

        private Map<String, Integer> emailIdx = new HashMap<>();
        private List<String> emails = new ArrayList<>();
        private Map<Integer, Integer> emailToAcc = new HashMap<>();
        private List<List<Integer>> adj;
        private Map<Integer, List<String>> emailGroup = new HashMap<>();
        private boolean[] visited;

        public List<List<String>> accountsMerge(List<List<String>> accounts) {

            int n = accounts.size();
            int m = 0;

            for (int index = 0; index < n; index++) {
                List<String> accountList = accounts.get(index);

                for (int acc = 0; acc < accountList.size(); acc++) {
                    String curEmail = accountList.get(acc);

                    if (!emailIdx.containsKey(curEmail)) {
                        emailIdx.put(curEmail, m);
                        emails.add(curEmail);
                        emailToAcc.put(m, index);
                        m++;
                    }
                }
            }

            this.adj = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                adj.add(new ArrayList<>());
            }

            for (List<String> acc : accounts) {
                for (int i = 2; i < acc.size(); i++) {
                    int idx1 = emailIdx.get(acc.get(i));
                    int idx2 = emailIdx.get(acc.get(i - 1));

                    adj.get(idx1).add(idx2);
                    adj.get(idx2).add(idx1);
                }
            }

            this.visited = new boolean[m];

            for (int i = 0; i < m; i++) {
                if (!visited[i]) {
                    int accId = emailToAcc.get(i);
                    emailGroup.putIfAbsent(accId, new ArrayList<>());
                    dfs(i, accId);
                }
            }

            List<List<String>> result = new ArrayList<>();
            for (int accId : emailGroup.keySet()) {
                List<String> group = emailGroup.get(accId);
                Collections.sort(group);

                List<String> merge = new ArrayList<>();
                merge.add(accounts.get(accId).get(0));
                merge.addAll(group);
                result.add(merge);
            }

            return result;
        }

        private void dfs(int node, int acctId) {
            visited[acctId] = true;
            emailGroup.get(acctId).add(emails.get(node));

            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    dfs(neighbor, acctId);
                }
            }
        }
    }

    // revised on 1/14/2026
    class SolutionRevisedOnDayThirty {

        Map<String, Integer> emailIndex = new HashMap<>();
        List<String> emails = new ArrayList<>();
        Map<Integer, Integer> emailToAcc = new HashMap<>();
        List<List<Integer>> adj = new ArrayList<>();

        Map<Integer, List<String>> emailToGrp = new HashMap<>();
        boolean[] visited;

        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            int n = accounts.size();
            int m = 0;

            for (int index = 0; index < n; index++) {
                List<String> accountList = accounts.get(index);

                for (int i = 1; i < accountList.size(); i++) {
                    String email = accountList.get(i);

                    if (!emailIndex.containsKey(email)) {
                        emailIndex.put(email, m);
                        emails.add(email);
                        emailToAcc.put(m, index);
                        m++;
                    }
                }
            }

            for (int i = 0; i < m; i++) {
                adj.add(new ArrayList<>());
            }

            for (List<String> acc : accounts) {
                for (int i = 2; i < acc.size(); i++) {
                    int idx1 = emailIndex.get(acc.get(i));
                    int idx2 = emailIndex.get(acc.get(i - 1));

                    adj.get(idx1).add(idx2);
                    adj.get(idx2).add(idx1);
                }
            }

            visited = new boolean[m];

            for (int i = 0; i < m; i++) {
                if (!visited[i]) {
                    int accId = emailToAcc.get(i);
                    emailToGrp.putIfAbsent(accId, new ArrayList<>());
                    dfs(i, accId);
                }
            }

            List<List<String>> result = new ArrayList<>();
            for (int accId : emailToGrp.keySet()) {
                List<String> group = emailToGrp.get(accId);
                Collections.sort(group);

                List<String> merged = new ArrayList<>();
                merged.add(accounts.get(accId).get(0));
                merged.addAll(group);
                result.add(merged);
            }

            return result;

        }

        private void dfs(int node, int acctId) {

            visited[acctId] = true;
            emailToGrp.get(acctId).add(emails.get(node));

            for (int nei : adj.get(node)) {
                if (!visited[nei]) {
                    dfs(nei, acctId);
                }
            }
        }
    }
}

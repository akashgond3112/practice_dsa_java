package main.interview.intercom;

/**
 * # Customer Support Assignment System
 *
 * ## Question
 *
 * In this exercise, you'll build a system to assign conversations to customer support agents.
 *
 * ## Task
 *
 * Implement an `AssignmentSystem` class that has the following API:
 *
 * ### Methods Required:
 *
 * **`initialize(agent_names)`**
 * - Initializes with a list of available agents
 *
 * **`set_limit(agent_name, limit)`**
 * - Sets the conversation limit for a specific agent
 *
 * **`assign(conversation_ids)`**
 * - Takes a list of conversation IDs, assigns them to the next available agents, and returns their names
 *
 * ## Requirements
 *
 * When assigning conversations, balance load evenly:
 *
 * - Assign new conversations to the agent with the **fewest conversations**
 * - If there are ties, pick the agent who's been **waiting the longest** since their last assignment
 * - Every agent can only take a limited maximum number of conversations (**default is 2**)
 *
 * ## Example
 *
 * ```java agents = ["Alice", "Bob", "Charlie"] system = AssignmentSystem(agents)
 *
 * system.set_limit("Bob", 4) system.set_limit("Charlie", 3)
 *
 * // I want to assign the first 4 conversations print(system.assign([1, 2, 3, 4])) // Output: ["Alice", "Bob",
 * "Charlie", "Alice"]
 *
 * system.set_limit("Alice", 3)
 *
 * // I want to assign another 5 conversations print(system.assign([5, 6, 7, 8, 9])) // Output: ['Bob', 'Charlie',
 * 'Alice', 'Bob', 'Charlie'] ```
 *
 * ## Expected Behavior Explanation
 *
 * **First Assignment [1, 2, 3, 4]:**
 * 1. Conversation 1 → Alice (0 conversations)
 * 2. Conversation 2 → Bob (0 conversations, but Alice now has 1)
 * 3. Conversation 3 → Charlie (0 conversations, but Alice and Bob each have 1)
 * 4. Conversation 4 → Alice (1 conversation vs Bob's 1, but Alice was assigned first so has been waiting longer)
 *
 * **Second Assignment [5, 6, 7, 8, 9]:**
 * - Alice: 2/3 conversations
 * - Bob: 1/4 conversations
 * - Charlie: 1/3 conversations
 *
 * Continue assigning based on fewest conversations and wait time tie-breaking.
 *
 * ## Constraints
 *
 * - Agent names are unique strings
 * - Conversation IDs are positive integers
 * - Agent limits are positive integers
 * - If all agents are at capacity, return appropriate indication
 *
 * ## Follow-up Questions to Consider
 *
 * 1. How would you handle the case where no agents are available?
 * 2. What's the time complexity of your solution?
 * 3. How would you modify this for real-time concurrent assignments?
 * 4. How would you persist agent state across system restarts?
 */

import java.util.*;

public class AssignmentSystem {

	// Agent data structure to track state
	private static class Agent {
		String name;
		int limit;
		int conversations;
		long lastAssignmentTime;

		Agent(String name) {
			this.name = name;
			this.limit = 2; // Default limit
			this.conversations = 0;
			this.lastAssignmentTime = 0;
		}
	}

	private final Map<String, Agent> agents;

	/**
	 * Initialize with a list of available agents
	 */
	public AssignmentSystem(List<String> agentNames) {
		this.agents = new HashMap<>();
		for (String name : agentNames) {
			agents.put(name, new Agent(name));
		}
	}

	/**
	 * Sets the conversation limit for a specific agent
	 */
	public void setLimit(String agentName, int limit) {
		Agent agent = agents.get(agentName);
		if (agent != null) {
			agent.limit = limit;
		}
	}

	/**
	 * Assigns conversations to agents based on:
	 * 1. Agent with the fewest conversations
	 * 2. If tied, agent who's been waiting longest since last assignment
	 */
	public List<String> assign(List<Integer> conversationIds) {
		List<String> assignments = new ArrayList<>();

		for (Integer ignored : conversationIds) {
			String selectedAgent = findBestAgent();
			if (selectedAgent != null) {
				assignments.add(selectedAgent);
				// Update agent state
				Agent agent = agents.get(selectedAgent);
				agent.conversations++;
				agent.lastAssignmentTime = System.currentTimeMillis();
			}
		}

		return assignments;
	}

	/**
	 * Find the best available agent based on load balancing rules
	 */
	private String findBestAgent() {
		Agent bestAgent = null;

		for (Agent agent : agents.values()) {
			// Skip agents who have reached their limit
			if (agent.conversations >= agent.limit) {
				continue;
			}

			// If no agent selected yet, or this agent has fewer conversations
			if (bestAgent == null || agent.conversations < bestAgent.conversations) {
				bestAgent = agent;
			}
			// If tied on conversation count, pick who's been waiting longest
			else if (agent.conversations == bestAgent.conversations && agent.lastAssignmentTime < bestAgent.lastAssignmentTime) {
				bestAgent = agent;
			}
		}

		return bestAgent != null ? bestAgent.name : null;
	}

	/**
	 * Get current status of all agents (useful for debugging)
	 */
	public void printStatus() {
		System.out.println("Agent Status:");
		for (Agent agent : agents.values()) {
			System.out.printf("%s: %d/%d conversations, last assigned: %d%n", agent.name, agent.conversations,
					agent.limit, agent.lastAssignmentTime);
		}
	}

	// Test the implementation
	public static void main(String[] args) {
		// Create system with agents
		List<String> agentNames = Arrays.asList("Alice", "Bob", "Charlie");
		AssignmentSystem system = new AssignmentSystem(agentNames);

		// Set limits
		system.setLimit("Bob", 4);
		system.setLimit("Charlie", 3);

		// First assignment
		List<Integer> conversations1 = Arrays.asList(1, 2, 3, 4);
		List<String> result1 = system.assign(conversations1);
		System.out.println("First assignment: " + result1);
		// Expected: [Alice, Bob, Charlie, Alice]

		// Update Alice's limit
		system.setLimit("Alice", 3);

		// Second assignment
		List<Integer> conversations2 = Arrays.asList(5, 6, 7, 8, 9);
		List<String> result2 = system.assign(conversations2);
		System.out.println("Second assignment: " + result2);
		// Expected: [Bob, Charlie, Alice, Bob, Charlie]

		// Print final status
		system.printStatus();
	}
}
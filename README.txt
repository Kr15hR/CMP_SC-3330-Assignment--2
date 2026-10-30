CMP_SC 3330 - Group Assignment #2: Campus IT Help Desk
IDE Used: Eclipse IDE for Java Developers

Design Decisions & Rules:
1. Double Resolution: A request cannot be resolved more than once. Any repeated attempt triggers an IllegalStateException.
2. Duplicate Assignment: A technician cannot be assigned the exact same request multiple times; HelpDesk.assignRequest throws an IllegalStateException if attempted.
3. NetworkIssueRequest Rule: Uses a weighted scoring formula: basePoints + (affectedUsers * 2).

GitHub Repository Link:
https://github.com/Kr15hR/CMP_SC-3330-Assignment--2
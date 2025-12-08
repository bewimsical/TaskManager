# Chore Quest

## Table of Contents
1. [Overview](#overview)
2. [Technologies Used](#technologies-used)
3. [Features](#features)
4. [Intended Users](#intended-users)
5. [How It Works](#how-it-works)
   - [Dashboard](#dashboard)  
   - [Rituals, Quests, and Bosses](#rituals-quests-and-bosses) 
   - [Social & Parties](#social--parties)
   - [Demo](#demo)
6. [Future Improvements / Next Steps](#future-improvements--next-steps)
7. [Contributors](#contributors)
   
## Overview
Chore Quest is a gamified task-management application built to turn everyday responsibilities into an engaging, RPG-style experience. Designed with clarity, security, and user experience at its core, the application helps users stay motivated by transforming chores into Rituals, Quests, and Bosses—each with its own progression system, visual indicators, and reward mechanics.

Users can create an account, set personalized cleaning goals, and track their progress through XP, levels, streaks, and meters that present information in a clear, game-inspired format. The interface prioritizes usability, providing intuitive navigation, straightforward controls, and automatically enabled parental settings for users under 18.

Chore Quest supports flexible task creation through premade templates or fully custom chores. Completing tasks grants XP with variable rewards to mirror traditional game design and keep the experience dynamic and fun. Optional social features allow users to add friends, compare progress, and collaborate on Quests and Bosses through shared parties.

Built as a full-stack application, Chore Quest showcases thoughtful UI design, consistent styling, and an immersive user experience—making it a standout project in both functionality and presentation.

## Technologies Used
- Java & JavaFX – Core application logic and user interface, providing a responsive, game-inspired design.
- Firebase Authentication – Secure account creation, login, and logout with industry-standard authentication.
- Firestore – Cloud database for storing user profiles, chore data, XP progress, and party information in real-time.
- Architecture & Design Patterns:
  - Layered Architecture – Keeps code organized, maintainable, and scalable.
  - MVVM (Model–View–ViewModel) – Separates UI from logic and improves testability.
  - Builder Pattern – Simplifies construction of complex data models and improves readability.

## Features
- **Gamified Task Management** – Turn chores into *Rituals* (daily), *Quests* (weekly), and *Bosses* (multi-task sessions) with XP rewards and level progression.  
- **Progress Tracking** – Visual indicators like streak counters, progress meters, and health bars make completing tasks satisfying and motivating.  
- **Customizable Chores** – Use premade templates or create your own tasks, with full editing and deletion capabilities.  
- **Secure Authentication** – Create an account, log in, and log out safely using Firebase Authentication.  
- **Parental Controls** – Automatically enabled for users under 18, restricting access to sensitive settings.  
- **Social Features** – Add friends, compare XP, and collaborate on Quests and Bosses through shared parties.  
- **Engaging UI** – User-friendly, game-inspired interface designed for easy navigation and immersive interaction.

## Intended Users

Chore Quest is designed for a broad range of users who want to stay organized and motivated with household tasks:

- **Individuals & Families** – Anyone looking to manage chores, track progress, and turn routine tasks into a fun, game-like experience.  
- **Parents & Guardians** – Can monitor and guide younger users with automatically enabled parental controls.  
- **Gamification Enthusiasts** – Users who enjoy game mechanics such as XP, levels, and progress tracking to stay engaged.  
- **Collaborative Users** – Friends or family members who want to work together on chores through shared Quests and Bosses.

## How it Works


Here’s a look at Chore Quest in action:

### Dashboard
![Dashboard Screenshot](https://github.com/user-attachments/assets/ef4780b4-b8b5-4988-b309-8383b93f0e40)  
*Overview of your XP, level, and active chores, with progress tracking visible at a glance.*

### Rituals, Quests, and Bosses

#### Rituals
![Rituals Page](https://github.com/user-attachments/assets/4d1c363d-c909-4b84-a7eb-846329d4a8c6)  
*Track daily Ritual chores with streak counters and earn XP for consistent completion.*

#### Quests
![Quests Page](https://github.com/user-attachments/assets/b0131984-568c-40ac-b244-562977cd556f)  
*Manage weekly Quests on an interactive map. Markers show each task, and a path visually progresses as chores are completed, giving a clear sense of advancement and accomplishment.*

#### Bosses
![Bosses Page](https://github.com/user-attachments/assets/ba96e2b7-676f-4dbd-8e6f-7d0b779fa840)
*Tackle large, multi-task chores in Boss sessions. The Boss image changes as tasks are completed—starting off angry and messy, and ending sparkly clean and happy once the boss is defeated, reinforcing a sense of accomplishment and progress.*


### Social & Parties

#### Social & Parties Page
![Social Screenshot](https://github.com/user-attachments/assets/06b113a5-bf34-4dbc-bdf0-d447e1c23c64)  
*Connect with friends, compare XP, and collaborate on shared Quests and Bosses, while tracking joint progress.*

#### Create Party Popup
![Social Screenshot](https://github.com/user-attachments/assets/59d59031-5db4-4973-8c3c-2909945ff226)  
*Create a new party to collaborate with friends or family on Quests and Bosses. The popup allows naming the party, selecting members, and customizing the session.*

### Demo

See Chore Quest in action: 

*Coming Soon*

### Completing a Ritual
![Completing a Ritual](path/to/ritual.gif)  
*Demonstrates completing a daily Ritual chore, tracking streaks, and earning XP.*

### Creating a Custom Quest
![Creating a Custom Quest](path/to/custom-quest.gif)  
*Shows how users can create a one-off quest, add custom chores, and save it to their task list.*

### Defeating a Boss
![Defeating a Boss](path/to/boss.gif)  
*Highlights a multi-task Boss session, with the health bar decreasing as chores are completed and XP rewards upon completion.*


## Future Improvements / Next Steps

Chore Quest serves as a starting point for a gamified task management platform, with numerous opportunities for growth and improvement.

- **Mobile Support** – Develop a mobile version of the app for iOS and Android.
- **Expanded Social Features** – Enable chat, notifications, and collaborative quests for friends and family. 
- **Gamification Enhancements** – Add badges, achievements, and randomized rewards to increase user engagement.  
- **Advanced Analytics** – Provide insights on user behavior, streak patterns, and productivity trends.
- **Automated Reminders** – Add notifications and reminders for upcoming chores or custom quests.  
- **Integration with External Calendars** – Sync custom quests with Google Calendar or other task management tools.  
- **Custom Boss & Map Images** – Users could upload their own images or browse community-created images to make Bosses and quests more personalized and visually engaging.
- **Customizable UI Themes** – Allow users to personalize the look and feel of the interface.  
- **Enhanced Security & Authentication** – Integrate multi-factor authentication and stricter parental controls.   

These enhancements showcase the potential for Chore Quest to grow into a comprehensive, engaging gamified task management platform.


## Contributors
- Kathleen Wims [GitHub](https://github.com/bewimsical)  
- Deannee Morris [GitHub](https://github.com/Dea-mvs)
- Lena Kemp [GitHub](https://github.com/CodebyLK)  
- Artwork by Megan Scheiner

  

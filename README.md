## Project Architecture
```
/app
 ├── data
 │    ├── remote
 │    │     ├── SupabaseClient.kt
 │    │     ├── AuthService.kt
 │    │     ├── UserService.kt
 │    │     ├── LessonsService.kt
 │    │     └── LeaderboardService.kt
 │    │
 │    ├── repository
 │    │     ├── AuthRepositoryImpl.kt
 │    │     ├── LessonsRepositoryImpl.kt
 │    │     ├── ProgressRepositoryImpl.kt
 │    │     ├── LeaderboardRepositoryImpl.kt
 │    │     └── UserRepositoryImpl.kt
 │    │
 │    ├── model
 │          ├── User.kt
 │          ├── Topic.kt
 │          ├── Lesson.kt
 │          ├── Question.kt
 │          ├── Progress.kt
 │          ├── LeaderboardEntry.kt
 │          └── StreakInfo.kt
 │
 ├── domain
 │     ├── repository
 │     │      ├── AuthRepository.kt
 │     │      ├── UserRepository.kt
 │     │      ├── LessonsRepository.kt
 │     │      ├── ProgressRepository.kt
 │     │      └── LeaderboardRepository.kt
 │     └── usecases
 │            ├── SignInWithGoogle.kt
 │            ├── GetUserStreak.kt
 │            ├── GetTopics.kt
 │            ├── GetLessonByTopic.kt
 │            ├── SubmitQuizAnswers.kt
 │            ├── UpdateProgress.kt
 │            └── GetLeaderboard.kt
 │
 ├── ui
 │    ├── screens
 │    │      ├── login
 │    │      │     ├── LoginViewModel.kt
 │    │      │     └── LoginScreen.kt
 │    │      ├── loading
 │    │      │     ├── LoadingViewModel.kt
 │    │      │     └── LoadingScreen.kt
 │    │      ├── home
 │    │      │     ├── HomeViewModel.kt
 │    │      │     └── HomeScreen.kt
 │    │      ├── topic
 │    │      │     ├── TopicViewModel.kt
 │    │      │     └── TopicScreen.kt
 │    │      ├── lesson
 │    │      │     ├── LessonViewModel.kt
 │    │      │     └── LessonScreen.kt
 │    │      ├── quiz
 │    │      │     ├── QuizViewModel.kt
 │    │      │     └── QuizScreen.kt
 │    │      ├── alphabet
 │    │      │     └── AlphabetScreen.kt
 │    │      ├── leaderboard
 │    │      │     ├── LeaderboardViewModel.kt
 │    │      │     └── LeaderboardScreen.kt
 │    │      └── components
 │    │            ├── TopicCard.kt
 │    │            ├── LessonCard.kt
 │    │            └── Navbar.kt
 │    │
 │    └── navigation
 │           ├── NavRoutes.kt
 │           └── AppNavigation.kt
 │
 └── utils
        ├── DateUtils.kt
        ├── Constants.kt
        └── Resource.kt

```

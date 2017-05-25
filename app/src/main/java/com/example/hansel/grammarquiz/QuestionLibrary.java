package com.example.hansel.grammarquiz;


public class QuestionLibrary {

    public static String mQuestions [] = {
            "Before she got that new job, Eda ______ lots of good books, but now she just doesn't have the time.",
            "Mr. Berry feels that the world today is not ______ it was during his childhood.",
            "John failed his exams because he was always out with his friends when he __________",
            "French _____________ in many parts of Europe.",
            "You _______________ by her.",
            "I am not worried about the exam. I ____________ enough to pass.",
            "How long ______________ music?",
            "Too much salt can _______ your blood pressure.",
            "I think my ankle ___________",
            "You will have to study for years _________ you intend to read the ancient Greek classics In the original.",
            "Although he is 75 years old and drinks and smokes, Mr. Winston doesn't have problems with his lungs,_________ does he suffer from a bad liver.",
            "There are certain books that every person ________ by the time he or she has graduated from university."

    };

    public static String mChoices [] [] = {
            {"ought to be reading", "used to read", "can read"},
            {"too safe for", "so safe that", "as safe as"},
            {"should have been studying", "must have been studying", "could be studying"},
            {"is being spoken", "is spoken", "has spoken"},
            {"will not recognize", "will not be recognized", "will not been recognized"},
            {"learnt", "have learnt", "had learnt"},
            {"are you learning", "have you been learning", "had you been learning"},
            {"affect", "effect", "affected"},
            {"is broken", "has been broken", "Either ‘is broken’ or ‘has been broken’"},
            {"if", "unless", "so"},
            {"rather", "nor", "whether"},
            {"ought to have read", "should be reading", "would read"}

    };

    public static String mCorrectAnswers[] =
            {"used to read", "as safe as", "should have been studying", "is spoken", "will not be recognized", "have learnt", "have you been learning", "affect","Either ‘is broken’ or ‘has been broken’", "if", "nor", "ought to have read"};

    public String getQuestion(int a){
        String question = mQuestions[a];
        return question;
    }

    public String getChoice1(int a){
        String choice0 = mChoices[a][0];
        return choice0;
    }

    public String getChoice2(int a){
        String choice1 = mChoices[a][1];
        return choice1;
    }

    public String getChoice3(int a){
        String choice2 = mChoices[a][2];
        return choice2;
    }

    public String getCorrectAnswer(int a){
        String answer = mCorrectAnswers[a];
        return answer;
    }
}

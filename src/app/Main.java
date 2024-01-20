package app;

import java.time.LocalDateTime;

import db.WeekJDBC;
import ihm.Constants;
import objects.Week;

public class Main {

	public static void main(String[] args) {		
		WeekJDBC jdbc = new WeekJDBC();
		
		Week w = new Week("WEEK_02",
						  LocalDateTime.now(),
						  "This week was special for multiple things. "
						+ "First, it was the first uni-week of the year. "
						+ "One exam got cancelled, which was a good news for nearly all of us. "
						+ "But the real thing that made this week good is simply that, saturday was my birthday. "
						+ "I just got 20 years old. Woaw... "
						+ "Thurdsay night, with Pema, Willem, Stuart, Enzo and Ibrahim we made Horrible pizzas. And it was the plan. "
						+ "We got a wheel turning to pick up random bad ingredients for our pizzas and i got Frangipane. Bro. "
						+ "It was an amazing night. But the real awesome night was saturday, the real day. "
						+ "I went to my mom's house for dinner and I knew Tatie Mélinda and Tonton Olivier would be invited and I was so happy about it. "
						+ "What I didn't know is that Yohan got invited by my mom too ! It was an amazing surprise. I loved the evening so much. "
						+ "The week is sadly getting a lower grade because of the weather, the classes and more importantly: the horrible week coming just after. "
						+ "In fact, I am writing this during this famous 'next week' and the amount of work we need to do is insane. "
						+ "Anyway, that's another week's review. "
						+ "Overall, was a good week.",
						  7.8f);
		
		jdbc.add(w);
	}
}

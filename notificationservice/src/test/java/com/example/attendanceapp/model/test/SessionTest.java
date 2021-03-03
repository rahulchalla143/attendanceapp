package com.example.attendanceapp.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.attendanceapp.model.Session;

public class SessionTest {
	
	Session auth = new Session();
	Session auth1 = new Session(123,"ab","ab","ab","ab","ab");
			

			@Test
			void testSessionid() {
				auth.setSessionid(123);
				assertEquals(auth.getSessionid(), 123);
			}

			@Test
			void testSessiondesc() {
				auth.setSessiondesc("sessiondesc");
				assertEquals(auth.getSessiondesc(), "sessiondesc");
			}

			@Test
			void testSessiondate() {
				auth.setSessiondate("sessiondate");
				assertEquals(auth.getSessiondate(),"sessiondate");
			}
			
			@Test
			void testSessiontime() {
				auth.setSessiontime("sessiontime");
				assertEquals(auth.getSessiontime(),"sessiontime");
			}
			
			@Test
			void testAvailableSlots() {
				auth.setAvailableslots("availableslots");
				assertEquals(auth.getAvailableslots(),"availableslots");
			}
			
			@Test
			void testFeedbackQuestion() {
				auth.setFeedbackquestion("feedbackquestion");
				assertEquals(auth.getFeedbackquestion(),"feedbackquestion");
			}
			
			@Test
			void testToString() {
				String string = auth1.toString();
				assertEquals(auth1.toString(),string);
			}

		}
		

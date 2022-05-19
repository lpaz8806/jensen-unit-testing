package com.example.demo;

import java.io.ObjectInputFilter;

public class DemoApplication {

	public static void main(String[] args) {
		SchemaInformation info = new SchemaInformation();
		try	{
			System.out.println(info.getPrimaryKeys());
		} catch(Exception e) {
			return;
		} finally {
			info.close();
		}
	}
}

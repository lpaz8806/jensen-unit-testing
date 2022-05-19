package com.example.demo;

public class DemoApplication {

	public static void main(String[] args) {
		SchemaInformation info = new SchemaInformation();
		try {
			ForeignKeysList foreignKeys = info.getForeignKeys();
			System.out.println(foreignKeys);
		} catch(Exception e) {
			return;
		} finally {
			info.close();
		}
	}
}

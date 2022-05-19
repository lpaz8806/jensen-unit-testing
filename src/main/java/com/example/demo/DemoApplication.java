package com.example.demo;

public class DemoApplication {

	public static void main(String[] args) {
		SchemaInformation info = new SchemaInformation();
		try	{
			ColumnsTypesList types = info.getTypes();
			Column countryNameCol = new Column("countries", "name");
			System.out.println(countryNameCol);
			ColumnType countryNameType = types.findByColumn(countryNameCol);
			System.out.println(countryNameType);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			info.close();
		}
	}
}

package com.technikum.MaintenanceMonitor;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MaintenanceMonitorApplicationTests {
	MaintenanceMonitorApplication maintenanceMonitor = new MaintenanceMonitorApplication();
	@Test
	void contextLoads() {
	}

	@Test
	void MaintenanceMonitor_GetMessage_Minus_Test()
	{
		// Arrange
		String expected = "-";

		// Act

		String message = maintenanceMonitor.maintenanceMode();

		// Assert
		assertEquals(expected, message);
	}

	@Test
	void MaintenanceMonitorController_SetMessage_Test()
	{
		// Arrange
		String expected = "This is a test.";

		// Act

		assertEquals(maintenanceMonitor.set("This is a test."), 1);
		String message = maintenanceMonitor.maintenanceMode();

		// Assert
		assertEquals(expected, message);
	}

	@Test
	void MaintenanceMonitorController_BlankMessage_Test()
	{
		// Arrange
		String expected = "-";

		// Act

		maintenanceMonitor.reset();
		String message = maintenanceMonitor.maintenanceMode();

		// Assert
		assertEquals(expected, message);
	}
}

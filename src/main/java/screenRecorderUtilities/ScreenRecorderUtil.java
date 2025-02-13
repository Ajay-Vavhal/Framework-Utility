package screenRecorderUtilities;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class ScreenRecorderUtil extends ScreenRecorder {
	public static ScreenRecorderUtil screenRecorder;
	public String name;
	
	static LocalDateTime currentDateTime = LocalDateTime.now();  
	static DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd-MMM-yyyy");  
	static String formatDateTime = currentDateTime.format(format1).toString(); 
	
	public ScreenRecorderUtil(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat,
			Format screenFormat, Format mouseFormat, Format audioFormat, File movieFolder, String name)
					throws IOException, AWTException {
		super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
		this.name = name;
	}

	@Override
	protected File createMovieFile(Format fileFormat) throws IOException {

		if (!movieFolder.exists()) {
			movieFolder.mkdirs();
		} else if (!movieFolder.isDirectory()) {
			throw new IOException("\"" + movieFolder + "\" is not a directory.");
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy-k_mm_ss a");
		return new File(movieFolder,
				name + " " + dateFormat.format(new Date()) + "." + Registry.getInstance().getExtension(fileFormat));
	}

	/**
	 * Starts the Screen recording 
	 * @param methodName
	 * @throws Exception
	 */
	public static void startRecord(String methodName) throws Exception {
		File file = new File("Test Scripts Recording\\"+formatDateTime+"\\");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;

		Rectangle captureSize = new Rectangle(0, 0, width, height);

		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().
				getDefaultScreenDevice()
				.getDefaultConfiguration();
		screenRecorder = new ScreenRecorderUtil(gc, captureSize,
				new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
						CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
						Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
				null, file, methodName);
		screenRecorder.start();
	}

	/**
	 * Stops the Screen recording 
	 * @throws Exception
	 */
	public static void stopRecord() throws Exception {
		screenRecorder.stop();
	}

	/**
	 * @param thresholdDays
	 */
	public static void deleteXDaysOldFilesFromRecordingFolder(int thresholdDays) {
		File path = new File(System.getProperty("user.dir")+"/Test Scripts Recording");
		File[] files = path.listFiles();
		for (File file : files) {
//			long cutOff = System.currentTimeMillis() - (thresholdDays * 24 * 60 * 60 * 1000);
//			long lastModified = file.lastModified();
			LocalDate today = LocalDate.now();
			String todayDate = today.toString();
			String todaysDate[] = todayDate.split("-");
//			System.out.println("Todays Date : "+ todaysDate[2]);

			String currentDate= todaysDate[2];

			String fileName = file.getName();
//			System.out.println("Selected filename :"+ file.getName());
			String fileNames[]= fileName.split("-");

			String fileModifiedDate = fileNames[0];
//			System.out.println("Selected file Date :"+ fileNames[0]);

			int dateDifference = Integer.parseInt(currentDate)- Integer.parseInt(fileModifiedDate) ;
			if (dateDifference < 0) {
				dateDifference	= dateDifference * -1; 
			}
			if (dateDifference >= thresholdDays) {
				file.delete();
			}
		}
	}
}
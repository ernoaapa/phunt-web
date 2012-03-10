package image;

import java.io.File;

public interface ImageProcessor {
	File resize(File image, int width, int height);
	File crop(File image, int width, int height);
}

package image;

import java.io.File;

public interface ImageProcessor {
	void resize(File image, int width, int height);
	void crop(File image, int width, int height);
}

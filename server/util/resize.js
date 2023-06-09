
const sharp = require('sharp');
const path = require('path');

class Resize {
  constructor(folder, originalname) {
    this.folder = folder;
    this.originalname = originalname
  }
  async save(buffer) {
    const filename = this.filename();
    const filepath = this.filepath(filename);

    await sharp(buffer)
      .resize(300, 300, { // size image 300x300
        fit: sharp.fit.inside,
        withoutEnlargement: true
      })
      .toFile(filepath);
    
    return filename;
  }
  filename() {
     // random file name
    return `${Date.now()}-${this.originalname}`;
  }
  filepath(filename) {
    return path.resolve(`${this.folder}/${filename}`)
  }
}
module.exports = Resize;

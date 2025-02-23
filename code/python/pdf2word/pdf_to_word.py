from pdf2docx import Converter

# Specify the file paths
pdf_file = "./practice_report.pdf"
docx_file = "./practice_report.docx"

# Create a Converter object
cv = Converter(pdf_file)

# Convert the PDF to a DOCX file
cv.convert(docx_file, start=3, end=9)

# Close the Converter object
cv.close()
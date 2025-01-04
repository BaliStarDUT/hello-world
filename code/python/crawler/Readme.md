# dev
- scrapy shell  https://www.cyjdm.com/50848.html     
1. a = response.css("#content > div > p:nth-child(3) > span > img:nth-child(1)")
2. a.attrib['src']
3. a = response.css("#content > div > p:nth-child(3) > span > img")
4. a[1].attrib['src']
# run
- scrapy  crawl image_spider
- scrapy crawl myspider -s LOG_FILE=scrapy.log

## selector
1. xpath: 
   1. //div[@class='content']/p/text()
   2. //*[@id="content"]/div/p[3]/span/img
2. css: style="font-family: 'andale mono', monospace;"
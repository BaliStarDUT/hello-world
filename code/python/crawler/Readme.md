# dev
- scrapy shell  https://www.cyjdm.com/50848.html     
1. a = response.css("#content > div > p:nth-child(3) > span > img:nth-child(1)")
2. a.attrib['src']
3. a = response.css("#content > div > p:nth-child(3) > span > img")
4. a[1].attrib['src']
5. response.xpath('/html/head/title/text()').extract()
6. 
# run
- scrapy  crawl image_spider
- scrapy crawl myspider -s LOG_FILE=scrapy.log
## source
1. https://www.cyjdm.com/
2. https://dimtown.com/p/vipcos
## selector
1. xpath: 
   1. //div[@class='content']/p/text()
   2. //*[@id="content"]/div/p[3]/span/img
2. css: style="font-family: 'andale mono', monospace;"

## 1. other
- https://www.digitalocean.com/community/tutorials/how-to-crawl-a-web-page-with-scrapy-and-python-3
- Crawlbase, a crawler that integrates seamlessly with Python. It offers a user-friendly interface and advanced features that streamline the crawling process. I’ve been impressed with its performance and flexibility. I highly recommend checking out Crawlbase as an alternative option for web crawling. Thank you for sharing this valuable resource!
- Scrapy uses a last in, first out (LIFO) strategy for
processing requests (depth first crawl). The last request you submit will be processed first.
This default is convenient for most of our cases.
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from webdriver_manager.chrome import ChromeDriverManager
import time
from urllib.parse import urlparse

op = Options()
op.add_argument('--headless')
s = Service(ChromeDriverManager().install())
driver = webdriver.Chrome(service=s, options=op)

visited_links = set() 

def crawl(url):
    if url in visited_links:
        return
    visited_links.add(url)
    
    driver.get(url)
    time.sleep(3)  

    texts = driver.find_elements(By.XPATH, "//span[@class='C9DxTc ']")
    if texts:
        for text in texts:
            print(text.text)
    else:
        print("No text found")

    
    links = driver.find_elements(By.TAG_NAME, 'a')
    for link in links:
        href = link.get_attribute('href')
        if href and urlparse(href).netloc == urlparse(url).netloc and href not in visited_links:
            crawl(href)    

crawl("https://sites.google.com/view/nikt2024?usp=sharing")

print(f'Link Count is {len(visited_links)}')
for link in visited_links:
    print(link)

driver.close()
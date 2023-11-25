import nltk 
from nltk.tokenize import word_tokenize

#Tag text for pos
text = word_tokenize("The people dislike the green dog")
textPos = nltk.pos_tag(text)

#Write to text file
textFile = open("lexicon.txt","w")
textFile.write("Word - POS - Root - Number\n\n")

# Add root and POS to each tuple
for word in textPos:
    if(word[1] == 'NNS'):
        textFile.write(word[0] +", "+ word[1]+ ", "+ word[0]+ ", 1 \n")
    elif (word[1] == 'VBP'):
        textFile.write(word[0] +", "+ word[1]+ ", "+ word[0]+ ", 1 \n")
    else:
        textFile.write(word[0] +", "+ word[1]+ ", "+ word[0]+ ", 0 \n")
    

textFile.close()
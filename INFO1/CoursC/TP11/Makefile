#
# Mettre ici le nom de votre programme 
# (il faut que dans OBJECTS il y ait au moins un objet de même nom que TARGET.
#
TARGET= taquin

#
# Mettre ici l'ensemble des fichiers objets nécessaires aux programmes 
# (les fichiers peuvent être situés dans des répertoires)
#
OBJECTS=  taquin.o move.o play.o

#
# Mettre ici vos options de compilation.
#
CFLAGS= -Wall -ansi -pedantic

#
# Mettre ici les noms des bibliothèques et leurs locations.
# Par exemple : -lpthread
#
LDLIBS= -lMLV -lm

 
###############################################################################

 
all: $(TARGET)
 
$(TARGET): $(DEPENDS) $(OBJECTS)
	$(CC) -o $@ $(LDFLAGS) $(OBJECTS) $(LDLIBS)
 
%.d:%.c
	$(CC) -M $(CFLAGS) -MT '$(shell echo "$&lt;" |sed 's,\($*\)\.c,\1.o $@,' )'  < > $@
 
include $(INCLUDE_DEPENDS)
 
clean:
	-rm -f $(TARGET)
	-rm -f $(OBJECTS)
	-rm -f $(DEPENDS)
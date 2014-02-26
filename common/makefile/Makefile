
objects=main1.o foo.o main2.o bar.o
mains=main1.o main2.o

target:
	@echo $(filter-out $(mains),$(objects))
	@echo $(filter $(mains),$(objects))
	@echo $(filter-out f%,$(objects))
	@echo $(filter-out f% b%,$(objects))
	
	

All: $(target)
ifndef target
	@echo "the goal: target required to be defined"
endif

#http://www.nondot.org/sabre/Mirrored/GNUMake/make_8.html#SEC73


from py4j.java_gateway import JavaGateway
gateway = JavaGateway()
instance = gateway.entry_point.getInstance()
gateway.help(instance)
c = instance.queryUnsettled()
print type(c)
# print c.encode('big5')
list = c.split()
num_items = len(list)/13
summary = list[0:13]
print num_items
print summary
print list

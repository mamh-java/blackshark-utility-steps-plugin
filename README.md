黑鲨自定义流水线step
=========


# blackshark-utility-steps-plugin


## readexcel 

    从excel文件读取，返回一个 列表,每个元素是一个字典
```groovy
def output = readExcel file: 'historyList.xlsx';
println output // 这里返回一个列表，每个元素是一个字典

```

## writeexcel 

    把一个二维表格写入到 excel 文件
```groovy
def list = [['key', 'value'], ['a', 'b']]
writeXlsx file: 'output.xlsx', records: list

```

## readhtml 
    从一个html文件读取某个标签内容，例如解析html文件中body下的内容
```groovy
def body =  readHtml file:_htmlfile, tag:"body"
println body
```

## readini 
    读取ini配置文件，从一个section读取某个option的值
```groovy
def value =  readIni file:_inifile, section:"build", option:"BUILD_COMBINATION"
println value
```

## checknode
    检查一个节点名是否存在
```groovy
def r =  checkNode node:bf-01
println r

```

## findnode
    给出一个节点标签名，获取这个标签下所有节点名
```groovy
def nodeList =  findNode
println nodeList

def nodeList =  findNode reg:"buildfarm"
println nodeList
```

## withconda
    暂未实现
```groovy

```

## readTOML
    读取 toml 配置文件
```groovy
def datas = readTOML file: 'dir/input.toml'
assert datas['title'] == 'TOML Example'
assert datas.title == 'TOML Example'
```







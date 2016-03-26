<Persons>
	<Message>FreeMarker Template build XML--->author:${name}</Message>
	<Persons>	
	<#list personDetails as person>
		<Person>	
			<Name>${person.name}</Name>
			<Location>${person.location}</Location>
		</Person>
	</#list>
	</Persons>
	<Books>
	<#list Book as book>
		<Book>
			<bookName>${book.name}</bookName>
			<bookPrice>${book.price}</bookPrice>
		</Book>
	</#list>
	</Books>
</Persons>
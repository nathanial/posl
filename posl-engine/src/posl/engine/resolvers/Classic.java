package posl.engine.resolvers;

import posl.engine.api.IArgumentHandler;
import posl.engine.core.Scope;
import posl.engine.type.Statement;

public class Classic extends IArgumentHandler {

	@Override
	public Object[] render(Scope scope, Statement tokens) {
		Object[] arguments = new Object[params.length];// should be 2
		arguments[0] = scope;
		arguments[1] = tokens;
		return arguments;
	}

}

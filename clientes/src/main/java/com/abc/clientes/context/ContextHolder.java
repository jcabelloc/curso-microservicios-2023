package com.abc.clientes.context;

import org.springframework.util.Assert;

public class ContextHolder {

  private static final ThreadLocal<Context> threadContext = new ThreadLocal<Context>();

  public static final Context getContext() {
    Context context = threadContext.get();

    if (context == null) {
      context = createEmptyContext();
      threadContext.set(context);

    }
    return threadContext.get();
  }

  public static final void setContext(Context context) {
    Assert.notNull(context, "Solo instancias non-null de Context son permitidas");
    threadContext.set(context);
  }

  public static final Context createEmptyContext(){
    return new Context();
  }
}

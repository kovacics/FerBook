import Router from 'next/router';

export async function redirect(context, route) {
  if (context.res) {
    context.res.writeHead(302, {
      Location: route,
    });
    context.res.end();
  } else {
    await Router.push(route);
  }
}

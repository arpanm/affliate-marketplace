import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import AiToolSession from './ai-tool-session';
import AiToolSessionDetail from './ai-tool-session-detail';
import AiToolSessionUpdate from './ai-tool-session-update';
import AiToolSessionDeleteDialog from './ai-tool-session-delete-dialog';

const AiToolSessionRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<AiToolSession />} />
    <Route path="new" element={<AiToolSessionUpdate />} />
    <Route path=":id">
      <Route index element={<AiToolSessionDetail />} />
      <Route path="edit" element={<AiToolSessionUpdate />} />
      <Route path="delete" element={<AiToolSessionDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default AiToolSessionRoutes;

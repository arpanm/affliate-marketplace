import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import AiToolChat from './ai-tool-chat';
import AiToolChatDetail from './ai-tool-chat-detail';
import AiToolChatUpdate from './ai-tool-chat-update';
import AiToolChatDeleteDialog from './ai-tool-chat-delete-dialog';

const AiToolChatRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<AiToolChat />} />
    <Route path="new" element={<AiToolChatUpdate />} />
    <Route path=":id">
      <Route index element={<AiToolChatDetail />} />
      <Route path="edit" element={<AiToolChatUpdate />} />
      <Route path="delete" element={<AiToolChatDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default AiToolChatRoutes;
